## Table of contents
* [General info](#general-info)
* [Cloud Info](#cloud-info)
* [Active Directory](#active-directory)
* [Backend_Deployment](#backend-deployment)
* [Frontend_Deployment](#frontend-deployment)
* [Data Migration to Cloud](#data-migration-to-azure)
* [Azure Functions](#azure-functions)

## General info
Moved the folowing components of the stock application to Azure Cloud 
* Mongo DB
* Backend microservices
* Angular dist folder

## Cloud Info
Got the Cloud subscription from IIHT
* ##### JSON - ![img_1.png](img_1.png)
* ##### Container Registry 
  * Created registry : fsestockmarketregistry via ACR
  * `az acr login -n fsestockmarketregistry` to login to that registry 
  * `az acr update -n fsestockmarketregistry --admin-enabled true` to make yourself an admin 
  * `docker login fsestockmarketregistry.azurecr.io` from your local pc to connect to this ACR 
  * Change docker image name with docker tag command : docker tag <old-image> <new-image>
  * Finally, `docker push fsestockmarketregistry.azurecr.io/hellosample` will push to ACR
  
## Active Directory
* Azure Active Directory -> Manage Tenants -> Create Tenant, Provided
  * Organization name : fse2-estockmarket
  * Initial Domain name : estocks.onmicrosoft.com
  * Went inside that resource : App registrations , Added estockAngular as Name
  * Now, to see this, you have to switch the subscription to fse2-estockmarket & AD -> Enterprise applications and add yourself as owner to it. 
  * Deleted this tenant, as we are supposed to create a Azure AD B2C. So registering the app in the same AD subscription
  * Azure AD -> +Add -> 
    * Name : fse2-stock-angular
    * Redirect URI - https://integrationstatic.z22.web.core.windows.net
    * After registration, Client Id is c51ef931-6bc6-403c-b7f1-6b0d35d9f9d9.
    * Make all code changes in VS code to enable MsalGuards

## Backend_Deployment
  * Pushed an image to ACR into **hellosample** repository, image is **latest**
  * Docker file should have java 11 zulu base image and
  * Build it using `docker build -f Dockerfile -t fsestockmarketregistry/userrepository .`
  * run it locally after building using `docker run -p 9052:9052 fsestockmarketregistry/userrepository .`
  * If it runs, then check for `http://localhost:9052/api/v1.0/user/getUsers` from postman
  * Then do `docker push fsestockmarketregistry/userrepository.`
  * Create a container instance , give a name like **usercontainer** and Ip is **Public**, DNS label is **userservice.westus.azurecontainer.io** with port **9052**
  * Hit `http://userservice.westus.azurecontainer.io:9052/api/v1.0/user` from postman for getting all users
  * In the same way, did for `http://containerservice.westus.azurecontainer.io:9053/api/` and also for `http://stockservice.westus.azurecontainer.io:9051/api/` .

## Frontend_Deployment
* Let angular CLI create the storage account for you , as **integrationstatic**
* In cmd of local dir of angular app, execute command : `ng add @azure/ng-deploy` to create a blob storage
* deploy the contents of dist folder into the blob storage container as a static web app. This command creates azure.json and updates angular.json. Automatically creates a resource group as **Integration-static-deploy**.
* `ng build --prod` to generate dist folder in local and Integration folder with all static js, css files
* `ng run Integration:deploy` to deploy it to Azure Blob storage.
* `https://integrationstatic.z22.web.core.windows.net/login` will host it in any browser
* Created CDN with name as stockCDN, pricing tier as CDN classic and CDN endpoint name as `http://stock-fse2.azureedge.net/` and provided the Origin hostname as above in the dropdown. This is done to host the frontend as http instead of https

## Data Migration to Cloud
* Azure Cosmos DB -> Mongo DB replacement, Created a resource group called EStockMarket and added
  * Account Name as stockcosmos with
    * location as West US, Used
    * capacity as Provided throughput as free tier account for 1000 RU's
    * Disabled Geo-Redundancy and multi-region writes
    * In connectivity method, Provided All networks.
    * Provided Periodic Backup Policy with
      * 24 hours backup interval and
      * 48 hours backup retention,
      * 2 copies retained
      * Automatic Data encryption by Service managed keys.
    * Created Tag with
    * key as cosmos and value as stock_market.
  * Click Create to deploy it under your resource group.
  * Using 2 databases in Cosmos DB, one for user, other for Company and stocks.
  
## Azure Functions
* Created Function app with name -> fsestockelk.azurewebsites.net
* Selected code, java 8.0
* Select hosting and provide the same blob storage that you've created.
* From app, do mvn clean package,
* mvn azure-functions:run,
* mvn azure-functions:deploy.