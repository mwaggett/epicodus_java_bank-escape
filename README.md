# Bank Escape!

##### _Can you escape before the guards get you?, {9/11/2015}_

#### By:

**Yelena Belikova, Maggie O'Neill, Daryl Seaver, Molly Waggett**

## Description
A game where you have to get out of the bank before the guards kill you. You are able to attack the guards back with either a weapon or a melee attack. Run for the door!

## Setup
* Set up the database in PostgreSQL by running the following commands in your terminal:
```
  psql
  CREATE DATABASE bank_escape;
  \c bank_escape;
```
To dump the sql file into the db navigate to the root directory of the project and run:
```
$ psql bank_escape < bank_escape.sql
```
* If you wish to run tests, create a test database:
```
  CREATE DATABASE bank_escape_test WITH TEMPLATE bank_escape;
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

Copyright (c) 2015 **Molly Waggett, Yelena Belikova, Daryl Seaver, Maggie O'Neill**

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
