# The Review Room: A Customer Feedback Platform

## Introduction

The Review Room is a customer review platform that allows customers to easily give feedback on products that they have bought, and for retailers to manage and view past reviews.

## Key Functionalities

### Seamless Review Collection:

Customers can effortlessly submit detailed reviews with ratings, providing feedback on product features, performance, and overall satisfaction.

### Centralized Review Management:

Retailers can manage all customer reviews in a single, user-friendly platform, allowing for efficient monitoring, analysis, and response.

### Data-Driven Product Insights:

By leveraging customer reviews, retailers gain valuable insights into product performance, identify trends, and make informed decisions regarding product selection, improvement, and marketing strategies.

## Project Structure

## The Review Room project utilizes a Spring Boot framework with clear separation of concerns:

Customer Controller: Manages CRUD operations for customer data. It also handles the creation of new reviews associated with a specific customer.
Product Controller: Manages CRUD operations for product data.
Review Controller: Manages CRUD operations for review data, including filtering reviews by specific criteria (ratings, customer ID, product ID).

## Test Coverage

This project prioritizes code quality and maintainability. We've included unit tests for the core functionalities of the application, covering all important aspects of the code. These tests ensure the code behaves as expected and helps prevent regressions during development.

## Front End

A basic front end to highlight an MVP of our project can be found [here](https://github.com/kiblykat/ReviewRoom-frontend).
