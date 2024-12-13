# Ecommerce Demo Test 
#### I Love It



## API Key
The test API key can be defined in the ECommerceApp class on the **RAPID_API_KEY** variable.


## Project Structure
This project follows the principles of **Clean Architecture** and separates the code into 3 layers:
- data 
- domain
- app


### Data
Contains the classes to retrieve information from the Rapid server and to manage the shopping cart.

### Domain
This is where the business logic lives. Due to the nature of this app it doesn't do much but has the advantage to show the functionalities the app support just by reading the classes names in this package.

- Search Product
- Get Product Info
- Save Product To Cart
- Get Cart 
- Clear Cart 
  
### App
The UI was coded using Jetpack Composable and ViewModels and  composed of **Screens** and **Components**. The components are reusable composables that live in multiple screens and contain all the logic to work independently. For example, to add the Cart UI and all it's functionality to another screen, one just need to include the Cart composable. A screen is a group of components.












