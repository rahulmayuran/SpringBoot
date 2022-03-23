package com.stock.market.Stock.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.market.Stock.Model.AggregateStocks;
import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class StockController {

	@Autowired
	StockService stockService;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping(value = "/stock/register")

	public ResponseEntity<Stock> addStock(@RequestBody Stock stock) throws JsonProcessingException {

		UUID setUUID = UUID.randomUUID();

		stock.setStockId(setUUID.toString());
		stock.setStockDate(new Date());

		Stock addOtherStock = stockService.addStock(stock);
		log.info("Company added " + addOtherStock);
		
		kafkaTemplate.send("fse_stock", mapper.writeValueAsString(" Added Stock -> "+addOtherStock));
		
		return new ResponseEntity<>(addOtherStock, HttpStatus.OK);

	}

	@SuppressWarnings("static-access")
	@DeleteMapping(value = "stock/delete/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable String id) {
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		try {
			stockService.deleteStock(id);
			response.ok("Deleted Stock with id" + id).status(HttpStatus.OK);
			kafkaTemplate.send("fse_stock", mapper.writeValueAsString("Deleted Stock with id" + id));
		} 
		catch (Exception e) {
			response.status(HttpStatus.BAD_REQUEST).body("Exception occured while deleting " + e);
			kafkaTemplate.send("fse_stock", "Exception while deleting, "+e.getMessage());
		}
		return response.badRequest().body("Id doesn't exist");
	}

	@GetMapping(value = "getStock/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable String id) throws JsonProcessingException{
		Optional<Stock> stock = stockService.getStockById(id);
		kafkaTemplate.send("fse_stock", mapper.writeValueAsString("Fetched stock with id, "+id+" -> "+stock));
		return ResponseEntity.of(stock);
	}

	@GetMapping(value = "/getStocks")
	public ResponseEntity<List<Stock>> getAllStocks() throws JsonProcessingException{
		List<Stock> stocks = stockService.getAllStocks();
		if (stocks.size() == 0) {
			return new ResponseEntity<List<Stock>>(HttpStatus.NOT_FOUND);
		}
		kafkaTemplate.send("fse_stock", mapper.writeValueAsString("Fetched all stocks, ->" + stocks));
		return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);

	}

	@GetMapping("/stock/search/{startDate}/{endDate}")
	public ResponseEntity<List<Stock>> dateBasedStocks(@PathVariable String startDate, @PathVariable String endDate) throws JsonProcessingException {
		if (startDate == null || endDate == null || startDate == "" || endDate == "") {
			log.info("Dates are null");
			kafkaTemplate.send("fse_stock", "Dates are null");
			return new ResponseEntity<List<Stock>>(HttpStatus.BAD_REQUEST);
		}
		try {
			stockService.getAllStocks().stream().forEach(e -> {
				log.info("Stock Date for (" + e.getCompanyName() + " is " + e.getStockDate() + ")");
				try {
					kafkaTemplate.send("fse_stock", mapper.writeValueAsString("Stock Date for (" + e.getCompanyName() + " is " + e.getStockDate() + ")"));
				} catch (JsonProcessingException e1) {
					e1.printStackTrace();
				}

			});

			log.info("Path variables -> " + startDate + " to " + endDate);

			List<Stock> resultStock = stockService.dateFilteredStocks(startDate, endDate);
			
			log.info("Spring :: found stocks from " + startDate + " to " + endDate + " are " + resultStock.size());
			kafkaTemplate.send("fse_stock",
					mapper.writeValueAsString("Spring :: found stocks from " + startDate + " to " + endDate + " are " + resultStock.size()));
			return new ResponseEntity<List<Stock>>(resultStock, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			kafkaTemplate.send("fse_stock", "No stocks as "+e.getMessage());
		}
		return new ResponseEntity<List<Stock>>(HttpStatus.BAD_GATEWAY);
	}

	@GetMapping(value = "/getStocks/price")
	public ResponseEntity<List<AggregateStocks>> getAllStocksByPrice() throws JsonProcessingException{
		List<Stock> stocks = stockService.getAllStocks();

		List<String> companyNames = stocks.stream().map(s -> s.getCompanyName()).distinct()
				.collect(Collectors.toList());
		log.info("Company Names list " + companyNames);

		List<AggregateStocks> aggregatedStocks = new ArrayList<AggregateStocks>();

		for (String companyName : companyNames) {
			// n lists will be created, n=number of companies
			List<Float> pricingList = stocks.stream().filter(s -> s.getCompanyName().equals(companyName))
					.map(p -> p.getStockPrice()).collect(Collectors.toList());

			log.info("Pricing List is " + pricingList);
			pricingList.forEach(s -> {
				log.info("Prices " + s);
			});

			float min = pricingList.stream().min(Comparator.comparing(Float::valueOf)).get();

			float max = pricingList.stream().max(Comparator.comparing(Float::valueOf)).get();

			float average = pricingList.stream().collect(Collectors.averagingDouble(num -> Double.valueOf(num)))
					.floatValue();

			float roundedAverage = (float) (Math.round(average * 100.0) / 100.0);

			AggregateStocks aggregate = new AggregateStocks();
			aggregate.setCompanyName(companyName);
			aggregate.setAvgStockPrice(roundedAverage);
			aggregate.setMinStockPrice(min);
			aggregate.setMaxStockPrice(max);

			aggregatedStocks.add(aggregate);
		}

		if (stocks.size() == 0) {
			return new ResponseEntity<List<AggregateStocks>>(aggregatedStocks, HttpStatus.NOT_FOUND);
		}
		kafkaTemplate.send("fse_stock", mapper.writeValueAsString("Aggregated stocks as per prices "+aggregatedStocks));
		return new ResponseEntity<List<AggregateStocks>>(aggregatedStocks, HttpStatus.OK);
	}

	@GetMapping(value = "/getStocks/aggregation")
	public List<AggregateStocks> getStocksByMongoDBAggregation() {
		List<Stock> stockList = stockService.getAllStocks();

		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("companyName")
						.in(stockList.stream()
						.map(e -> e.getCompanyName())
						.collect(Collectors.toList() ))),
				Aggregation.group("companyName").min("companyName").as("companyName")
						.avg("stockPrice").as("avgStockPrice")
						.min("stockPrice").as("minStockPrice")
						.max("stockPrice").as("maxStockPrice"),
				Aggregation.project("companyName", "avgStockPrice", "minStockPrice", "maxStockPrice"));

		// mongoTemplate.aggregate(Aggregation instance, Collection/Table Name,ResultClassToBeMapped)
		AggregationResults<AggregateStocks> groupResults = mongoTemplate.aggregate(aggregation, "stock",
				AggregateStocks.class);
		List<AggregateStocks> aggregatedStocks = groupResults.getMappedResults();
		return aggregatedStocks;
	}

	@GetMapping(value = "/getPrices/aggregated/kafka")
	public List<AggregateStocks> getStocksByMongoAndKafka() {

		List<Stock> stockList = stockService.getAllStocks();
		try {

			Aggregation aggregation = Aggregation.newAggregation(
					Aggregation.match(Criteria.where("companyName")
							.in(stockList.stream()
							.map(e -> e.getCompanyName())
							.collect(Collectors.toList()))),
					Aggregation.group("companyName").min("companyName").as("companyName")
							.avg("stockPrice").as("avgStockPrice")
							.min("stockPrice").as("minStockPrice")
							.max("stockPrice").as("maxStockPrice"),
					Aggregation.project("companyName", "avgStockPrice", "minStockPrice", "maxStockPrice"));

			// mongoTemplate.aggregate(Aggregation instance, Collection/Table Name,ResultClassToBeMapped)
			AggregationResults<AggregateStocks> groupResults = mongoTemplate.aggregate(aggregation, "stock",
					AggregateStocks.class);
			List<AggregateStocks> aggregatedStocks = groupResults.getMappedResults();
			String aggregatedStockString = mapper.writeValueAsString(aggregatedStocks);
			kafkaTemplate.send("fse_stock", aggregatedStockString);
			return aggregatedStocks;
		} catch (Exception e) {
			kafkaTemplate.send("fse_stock", e.getMessage());
			return new ArrayList<>();
		}
	}
}
