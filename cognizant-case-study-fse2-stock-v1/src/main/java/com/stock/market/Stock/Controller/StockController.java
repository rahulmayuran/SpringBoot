package com.stock.market.Stock.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.Stock.Model.AggregateStocks;
import com.stock.market.Stock.Model.Stock;
import com.stock.market.Stock.Service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Tag(name = "stock", description = "The stocks API")
public class StockController {

    @Autowired
    StockService stockService;

    @Operation(summary = "Add a new stock", description = "", tags = { "stock" })
    @PostMapping(value = "/stock/register")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) 
    {
 	
    	UUID setUUID = UUID.randomUUID();
   
		stock.setStockId(setUUID.toString());
		stock.setStockDate(new Date());
		
    	Stock addOtherStock = stockService.addStock(stock);
        System.out.println("Company added "+addOtherStock);
        return new ResponseEntity<>(addOtherStock, HttpStatus.OK);
    	
    }

    @Operation(summary = "Deletes a Stock", description = "", tags = { "stock" })
    @DeleteMapping(value ="stock/delete/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id)
    {
    	if(id!=null)
    	{
    		stockService.deleteStock(id);
	        return new ResponseEntity<String>("Deleted Stock",HttpStatus.ACCEPTED);
    	}
    	return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
      
    }
    
    @Operation(summary = "Find stock by ID", description = "Returns a single stock", tags = { "stock" })
    @GetMapping(value = "getStock/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable String id)
    {
    		Optional<Stock> stock = stockService.getStockById(id);
			return ResponseEntity.of(stock);
    }
    
    @Operation(summary = "Find stock by CompanyName", description = "Returns a list of stocks", tags = { "stock" })
    @GetMapping(value = "getStock/{name}")
    public ResponseEntity<List<Stock>> getStockByCompanyName(@PathVariable String name)
    {
    		Optional<List<Stock>> stock = stockService.getStockByCompanyName(name);
			return ResponseEntity.of(stock);
    }
    
    @Operation(summary = "Fetch all stocks", description = "", tags = { "stock" })
    @GetMapping(value ="/getStocks")
    public ResponseEntity<List<Stock>> getAllStocks()
    {
        List<Stock> stocks = stockService.getAllStocks();
        if(stocks.size()==0) {
        	return new ResponseEntity<List<Stock>>(HttpStatus.NOT_FOUND);
        }
        stocks.forEach(System.out::println);
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }
    
    @GetMapping("/stock/search/{startDate}/{endDate}")
    public ResponseEntity<List<Stock>> dateBasedStocks(@PathVariable String startDate, @PathVariable String endDate)
    {
    	if(startDate==null||endDate == null||startDate==""||endDate=="") {
    		System.out.println("Dates are null");
    		return new ResponseEntity<List<Stock>>(HttpStatus.BAD_REQUEST);
    	}
    	try { 
    		stockService.getAllStocks()
    		.stream()
    		.forEach(e->
    		{
    			System.out.println("Stock Date for ("+e.getCompanyName()+" is "+e.getStockDate()+")");
    		});
    		
    		System.out.println("Path variables -> "+startDate+" to "+endDate);
    		
    		List<Stock> resultStock = stockService.dateFilteredStocks(startDate, endDate);
    		System.out.println("Spring :: found stocks from "+startDate+" to "+endDate+" are "+resultStock.size());
   
			return new ResponseEntity<List<Stock>>(resultStock, HttpStatus.OK);
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return new ResponseEntity<List<Stock>>(HttpStatus.BAD_GATEWAY);
	}
    
    @Operation(summary = "Fetch all stocks based on Company", description = "", tags = { "stock" })
    @GetMapping(value ="/getStocks/price")
    public ResponseEntity<List<AggregateStocks>> getAllStocksByPrice()
    {
        List<Stock> stocks = stockService.getAllStocks();

        //["A","B","C"]
        List<String> companyNames = stocks.stream()
        		.map(s->s.getCompanyName())
        		.distinct()
        		.collect(Collectors.toList());
        
        List<AggregateStocks> aggregatedStocks = new ArrayList<AggregateStocks>();
        
        for (String companyName : companyNames) 
        {
        	//[45.44, 32.22, 102.33, 34.33, 22.35, 21.22]
        	List<Float> pricingList = stocks.stream()
        			.filter(s->s.getCompanyName().equals(companyName))
        			.map(p->p.getStockPrice())
        			.collect(Collectors.toList());
        	
        	float min = pricingList.stream()
        			 .min(Comparator.comparing(Float::valueOf))
                    .get();
        	
        	float max = pricingList.stream()
       			 .max(Comparator.comparing(Float::valueOf))
                   .get();
        	
        	float average = pricingList.stream()
        			.collect(Collectors.averagingDouble(num -> Double.valueOf(num)))
        			.floatValue();
        	
        	float roundedAverage = (float) (Math.round(average * 100.0) / 100.0);	
        	
        	AggregateStocks aggregate = new AggregateStocks();
        	aggregate.setCompanyName(companyName);
        	aggregate.setAvgStockPrice(roundedAverage);
        	aggregate.setMinStockPrice(min);
        	aggregate.setMaxStockPrice(max);
        	
        	aggregatedStocks.add(aggregate);
		}
        
        
        if(stocks.size()==0) 
        {
        	return new ResponseEntity<List<AggregateStocks>>(HttpStatus.NOT_FOUND);
        }
        stocks.forEach(System.out::println);
        return new ResponseEntity<List<AggregateStocks>>(aggregatedStocks, HttpStatus.OK);
    }
}
