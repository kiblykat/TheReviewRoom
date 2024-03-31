# The Review Room: A Customer Feedback Platform
## Introduction

The Review Room is a comprehensive customer feedback platform designed to empower retailers and their customers. It fosters open communication by enabling customers to share valuable product reviews, while providing retailers with actionable insights to optimize product offerings and enhance the overall shopping experience.

## Key Functionalities

Seamless Review Collection: Customers can effortlessly submit detailed reviews with ratings, providing valuable feedback on product features, performance, and overall satisfaction.
Centralized Review Management: Retailers can manage all customer reviews in a single, user-friendly platform, allowing for efficient monitoring, analysis, and response.
Data-Driven Product Insights: By leveraging customer reviews, retailers gain valuable insights into product performance, identify trends, and make informed decisions regarding product selection, improvement, and marketing strategies.
Project Structure

## The Review Room project utilizes a Spring Boot framework with clear separation of concerns:

Customer Controller: Manages CRUD operations (Create, Read, Update, Delete) for customer data. It also handles the creation of new reviews associated with a specific customer.
Product Controller: Manages CRUD operations for product data.
Review Controller: Manages CRUD operations for review data, including filtering reviews by specific criteria (ratings, customer ID, product ID).

## Test Coverage

This project prioritizes code quality and maintainability.  We've included unit tests for the core functionalities of the application, covering all important aspects of the code. These tests ensure the code behaves as expected and helps prevent regressions during future development.

## Front End

A basic front end to highlight an MVP of our project can be found at: https://github.com/kiblykat/ReviewRoom-frontend
