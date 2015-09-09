# Application Name

##### _{Brief description of application}, {Date of current version}_

#### By **Molly Waggett**

## Description

{This is a detailed description of your application. Give as much detail as needed to explain what the application does, and any other information you want users or other developers to have. Do you want other developers to use this code in their apps? Talk about things like that here in simple terms.}

## Setup

* Set up the database in PostgreSQL by running the following commands in your terminal:
```
  psql
  CREATE DATABASE database_name;
  \c database_name;
  CREATE TABLE table_name (id serial PRIMARY KEY, attribute varchar);
```
* If you wish to run tests, create a test database:
```
  CREATE DATABASE database_name_test WITH TEMPLATE database_name;
```
* Clone this repository.
* Using the command line, navigate to the top level of the cloned directory.
* Make sure you have gradle installed. Then run the following command in your terminal:
```
  gradle run
```
* Go to localhost:4567.
* Go!

## Technologies Used

* Java
* PostgreSQL
* Spark
* Velocity
* Gradle
* JUnit
* FluentLenium

### Legal

Copyright (c) 2015 **Molly Waggett**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
