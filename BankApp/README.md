# Multi-Currency Bank Account Application

## Project Documentation

### Application Description

#### Goal and Mandatory Requirements
The application is a comprehensive console-based banking system that demonstrates object-oriented programming principles, design patterns, and clean architecture. The core goal is to provide a functional banking application that handles multi-currency accounts with automatic conversion and persistent data storage.

**Mandatory Requirements Implemented:**
- Object-oriented design with at least 8 self-written classes (13+ classes implemented)
- At least 800 lines of code (1393+ lines implemented)
- Clean separation of concerns (UI, Logic, Data persistence)
- User input validation with exception handling
- Persistent data storage using JSON files
- At least two design patterns (Strategy and Factory patterns)
- Interface usage
- Delegation and polymorphism implementation

#### Nice-to-Have Features
- Live exchange rate integration with Frankfurter.dev API
- Automatic fallback to fixed rates when API unavailable
- Comprehensive transaction history with timestamps
- Fee system with configurable policies
- Multiple currency support (USD, EUR, GBP, CHF, JPY)
- Detailed error handling and user-friendly messages

### Use Cases

1. **Create Bank Account**: User can create a new bank account by providing owner name, initial balance (minimum 0), and selecting currency
2. **View All Accounts**: User can view a list of all existing accounts with their current balances and status
3. **Deposit Money**: User can deposit money into an existing account, with automatic transaction logging
4. **Withdraw Money**: User can withdraw money from an account, with validation for sufficient funds
5. **Transfer Money Between Accounts**: User can transfer money between accounts, with automatic currency conversion if needed and fee application
6. **View Transaction History**: User can view complete transaction history for any account, filtered by date range if desired
7. **Save Data**: User can manually save all account and transaction data to persistent storage
8. **Exit Application**: User can exit the application with or without saving changes

### Design Patterns Implementation

#### Strategy Pattern
**ExchangeRateProvider Interface**: Defines a common interface for different exchange rate sources
- **FrankfurterProvider**: Implements live API calls to Frankfurter.dev for real-time rates
- **FixedRateProvider**: Provides predefined fixed rates for testing and fallback scenarios

**Justification**: This pattern allows the application to switch between different exchange rate sources dynamically without changing client code. It enables easy addition of new rate providers and provides fallback capability when the live API is unavailable.

**FeePolicy Interface**: Defines fee calculation strategies
- **FlatFeePolicy**: Implements a fixed fee per transaction

**Justification**: Enables flexible fee structures that can be changed without modifying transfer logic. Supports different fee models (percentage, tiered, etc.) through polymorphism.

#### Factory Pattern
**AccountFactory Interface**: Defines account creation contract
- **BasicAccountFactory**: Creates standard bank accounts

**Justification**: Encapsulates account creation logic, making it easy to add new account types (e.g., PremiumAccount, SavingsAccount) without modifying client code. Promotes loose coupling and extensibility.

### Inheritance Hierarchy

**No inheritance hierarchy is implemented in this application.**

**Explanation**: The domain model is designed around composition and interfaces rather than inheritance because:
- All account types currently share the same behavior and attributes
- The Factory pattern provides the flexibility to create different account types without inheritance
- Strategy pattern handles behavioral variations through composition
- Interfaces provide better decoupling than inheritance hierarchies
- The current requirements don't necessitate different account behaviors that would benefit from inheritance

### Clean Code Principles Applied

#### Single Responsibility Principle (SRP)
- **AccountService**: Only handles account management operations
- **ExchangeService**: Only handles currency conversion logic
- **TransferService**: Only handles money transfer operations
- **AccountRepository**: Only handles data persistence

#### Don't Repeat Yourself (DRY)
- Money operations (add, subtract, convert) centralized in Money class
- Transaction creation logic centralized in Account class
- Error handling patterns consistent across services

#### Meaningful Names
- Classes named clearly: `ExchangeRateProvider`, `FlatFeePolicy`, `TransferService`
- Methods describe actions: `calculateFee()`, `convertCurrency()`, `performTransfer()`
- Variables use descriptive names: `sourceAccount`, `destinationAccount`, `exchangeRate`

#### Small, Focused Methods
- Each method has a single purpose (e.g., `deposit()`, `withdraw()`, `transfer()`)
- Complex operations broken into smaller private methods
- Methods follow the "do one thing" principle

#### Error Handling
- Custom exceptions (e.g., `ExchangeRateException`, `TransferException`)
- Clear error messages for user input validation
- Graceful degradation (API fallback to fixed rates)

#### SOLID Principles
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: New exchange providers or fee policies can be added without modifying existing code
- **Liskov Substitution**: Interface implementations are interchangeable
- **Interface Segregation**: Small, focused interfaces
- **Dependency Inversion**: High-level modules depend on abstractions, not concretions

## Features

### Core Banking Features
- **Account Management**: Create, view, and manage multiple bank accounts
- **Multi-Currency Support**: Handle accounts in USD, EUR, GBP, CHF, and JPY
- **Deposit & Withdrawal**: Add and withdraw money from accounts with validation
- **Money Transfers**: Transfer funds between accounts with automatic currency conversion
- **Transaction History**: Complete transaction logging and history viewing
- **Data Persistence**: Automatic saving and loading of accounts and transactions to JSON files

### Advanced Features
- **Live Exchange Rates**: Integration with Frankfurter.dev API for real-time currency conversion
- **Fee System**: Configurable fee policies (currently flat fee implementation)
- **Design Patterns**: Implementation of Strategy and Factory patterns
- **Clean Architecture**: Modular design with clear separation of concerns

## Architecture

### Project Structure

```
src/main/java/bankapp/
├── App.java                          # Main application entry point
├── cli/
│   └── BankCliController.java        # Command-line interface controller
├── domain/
│   ├── Account.java                  # Bank account entity
│   ├── Money.java                    # Immutable money value object
│   ├── Transaction.java              # Transaction record
│   └── TransactionType.java          # Transaction type enumeration
├── factory/
│   ├── AccountFactory.java           # Factory interface for accounts
│   └── BasicAccountFactory.java      # Basic account factory implementation
├── persistence/
│   ├── AccountRepository.java         # JSON persistence for accounts
│   ├── JsonCurrencyAdapter.java      # Gson adapter for Currency
│   └── JsonLocalDateTimeAdapter.java # Gson adapter for LocalDateTime
└── service/
    ├── AccountService.java           # Account management service
    ├── ExchangeService.java          # Currency exchange service
    ├── TransferService.java          # Money transfer service
    ├── exchange/
    │   ├── ExchangeRateProvider.java # Strategy interface for exchange rates
    │   ├── ExchangeRateException.java
    │   ├── FixedRateProvider.java    # Fixed rate implementation
    │   └── FrankfurterProvider.java  # Live API rate provider
    └── fee/
        ├── FeePolicy.java            # Strategy interface for fees
        └── FlatFeePolicy.java        # Flat fee implementation
```

## Technology Stack

- **Language**: Java 21
- **Build Tool**: Gradle
- **JSON Processing**: Gson 2.10.1
- **HTTP Client**: Java 11+ built-in HttpClient
- **Exchange Rate API**: Frankfurter.dev

## Getting Started

### Prerequisites
- Java 21 or higher
- Gradle (or use included Gradle wrapper)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/leon-prob/m320

   cd BankApp
   ```

2. **Run the main class**

## Usage Guide

### Main Menu Options

1. **Create Account**
   - Enter account owner name
   - Set initial balance (minimum 0)
   - Choose currency (USD, EUR, GBP, CHF, JPY)

2. **View Accounts**
   - Display all existing accounts with balances

3. **Deposit Money**
   - Select account
   - Enter deposit amount
   - Transaction is logged automatically

4. **Withdraw Money**
   - Select account
   - Enter withdrawal amount
   - Validates sufficient funds

5. **Transfer Money**
   - Select source and destination accounts
   - Enter transfer amount
   - Automatic currency conversion if needed
   - Applies transfer fees

6. **View Transaction History**
   - Select account
   - View all transactions with timestamps and details

7. **Save and Exit**
   - Saves all data to JSON files
   - Safely exits application

8. **Exit without Saving**
   - Exits without saving changes

### Supported Currencies

- **USD**: US Dollar
- **EUR**: Euro
- **GBP**: British Pound
- **CHF**: Swiss Franc
- **JPY**: Japanese Yen

### Exchange Rate Information

The application uses live exchange rates from Frankfurter.dev API with fallback to fixed rates:

- **Live Rates**: Real-time rates fetched from Frankfurter.dev
- **Fallback Rates**: Predefined fixed rates for testing/offline use
- **Automatic Fallback**: Switches to fixed rates if API is unavailable

## Configuration

### Fee Policy

The application uses a flat fee policy by default:
- **Fee Amount**: 1.00 USD per transfer
- **Currency**: Applied in USD equivalent

To modify the fee policy, edit the `BankCliController` constructor:

```java
// Change fee amount or currency
FeePolicy feePolicy = new FlatFeePolicy(new BigDecimal("2.50"), Currency.getInstance("EUR"));
```

## Data Persistence

### Saving
- Accounts and transactions can be saved to `accounts.json`
- Data is loaded on application startup
- Manual save available through "Save and Exit" option

### Data Structure

The application stores:
- Account information (ID, owner, balance, currency)
- Complete transaction history
- Timestamps for all operations
- Exchange rates used in conversions

## Error Handling

The application includes comprehensive error handling:

- **Input Validation**: Validates all user inputs
- **Insufficient Funds**: Prevents overdrafts
- **API Failures**: Graceful fallback to fixed rates
- **File I/O Errors**: Handles persistence failures
- **Network Issues**: Timeout handling for API calls

## AI Assistance Declaration

This project was developed with AI assistance for:
- Implementation of complex algorithms (API integration, JSON serialization)
- Design pattern recommendations and implementations
- Code review and optimization suggestions
- Documentation structure and content

AI was used as a tool to accelerate development while ensuring all final code is manually reviewed.

## Reflection: Learning Process

### Object-Oriented Programming Concepts
This project provided hands-on experience with core OOP principles:

**Encapsulation**: The `Money` class encapsulates amount and currency, providing controlled access through methods. The `Account` class encapsulates account state and behavior, ensuring data integrity.

**Abstraction**: Interfaces like `ExchangeRateProvider` and `FeePolicy` abstract away implementation details, allowing for flexible and extensible designs.

**Polymorphism**: The Strategy pattern implementations demonstrate runtime polymorphism, where different exchange rate providers and fee policies can be used interchangeably.

**Inheritance vs Composition**: The project explored when to use inheritance (not used here due to uniform account behavior) versus composition (used extensively with services and strategies).

### Design Patterns
**Strategy Pattern**: Learned how to define families of algorithms (exchange rates, fee calculation), encapsulate each one, and make them interchangeable. This pattern proved invaluable for the exchange rate fallback system.

**Factory Pattern**: Gained experience in encapsulating object creation logic, making the system extensible for different account types without modifying existing code.

### Clean Code Principles
**Single Responsibility**: Breaking down complex classes into focused services taught the importance of each class having one reason to change.

**Dependency Inversion**: Using interfaces rather than concrete classes for dependencies made the code more testable and maintainable.

**DRY Principle**: Centralizing common operations (like money arithmetic, transaction creation) reduced code duplication and improved maintainability.

### Technical Skills
**API Integration**: Implementing HTTP client calls with proper error handling and fallback mechanisms.

**JSON Serialization**: Working with Gson and custom adapters for complex objects like Currency and LocalDateTime.

**Exception Handling**: Designing custom exceptions and implementing comprehensive error handling strategies.

**Testing**: Understanding the importance of unit tests for services and integration tests for data persistence.

### Challenges and Solutions
**Currency Handling**: Initially struggled with BigDecimal precision, but learned to handle monetary calculations properly.

**API Reliability**: Implemented fallback mechanisms when learning that external APIs can fail.

**Data Persistence**: Complex serialization of nested objects required custom adapters and careful design.

### Future Improvements
The project demonstrated how a well-architected system can easily accommodate new features. The patterns used make it straightforward to add new account types, exchange providers, or fee policies without major refactoring.

This project significantly enhanced understanding of how to design maintainable, extensible software systems using OOP principles and design patterns.