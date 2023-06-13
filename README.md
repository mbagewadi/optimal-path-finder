# Find the Optimal Path between two countries with graph traversal algorithm  

### Project Description

The "OptimalPathFinder" project aims to find the optimal path between two countries, identified by their cca3 codes, with respect to the resource provided. The resource is a comprehensive dataset of countries available at the following link: [Resource Link](https://mledoze.github.io/countries/).

The project utilizes graph traversal techniques, like the Breadth-First Search (BFS) algorithm, to explore the relationships between countries and determine the most efficient route.

By employing the "OptimalPathFinder," users can quickly and accurately identify the best route between two countries based on their chosen mode of transportation. Whether planning a motorcycle adventure or a road trip, this project provides valuable insights and helps optimize travel routes. 

### Used Dependencies
* Core
    * Spring
        * Spring Boot
        * Spring Web
        * Spring Security
* Lombok
* Feign

### Technologies
* Java 17
* Spring Boot
* Restful API
* Lombok
* Maven
* Junit5
* Mockito
* Docker

###  Run the Application

#### Maven

<b>1 )</b> Download your project from this link shown below
```
    git clone https://github.com/mbagewadi/optimal-path-finder.git
```

<b>2 )</b> Go to the project's home directory shown below
```
    cd optimal-path-finder
```

<b>3 )</b> Create a jar file though this command shown below
```
    mvn clean install
```

<b>4 )</b> Run the project though this command shown below
```
    mvn spring-boot:run
```

#### Docker
<b>1 )</b> Build the image through Docker
```
    docker build -t optimal-path-finder .
```
<b>2 )</b> Run the command
```
    docker run -p8080:8080 optimal-path-finder
```


Explore Rest APIs
<table style="width:100%">
  <tr>
    <th>Method</th>
    <th>Url</th>
    <th>Description</th>
    <th>Valid Request Body</th>
    <th>Valid Request Params</th>
    <th>Valid Request Params and Body</th>
    <th>No Request or Params</th>
  </tr>
  <tr>
      <td>GET</td>
      <td>route</td>
      <td>Find the optimal path</td>
      <td><a href="README.md#route">Info</a></td>
      <td></td>
      <td></td>
      <td></td>
  </tr>
</table>


## Valid Request Body

##### <a id="route">Find the optimal path
```
    http://localhost:8080/route
    
    {
       "from": the cca3 country code
       "to": the cca3 country code
    }
```


### Screenshot

<details>
<summary>Click here to show the screenshot of project result</summary>
    <p> Picture 1 </p>
    <img src ="/screenshot/postman-1.PNG">
</details>    