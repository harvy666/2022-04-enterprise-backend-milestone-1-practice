# Library
In this project you will have to implement a backend for a library.

## Notes
- It is not a requirement, but it helps if you divide your application into controller, service and persistence (dao) layers.
- Lombok is not available for this project.
- Don't add any new dependencies to pom.xml, because their availability is not guaranteed on Qualified.
- Don't use database for persistence. Store data in memory.

## Entities
#### Book
- `String title`
- `String writerName`
- `String genre`
- `int numberOwnedByLibrary`: number of copies owned by the library.
- `int numberBorrowed`: number of book currently borrowed.

#### Writer
- `String name`
- `String birthPlace`

## Requirements
- You can store a new writer when a `post` request arrives to the `/writer` endpoint, with a `Writer` JSON in the request body.
- You can store a new book when a `post` request arrives to the `/book` endpoint, with a `Book` JSON in the request body.
- A `get` request to the `/book` endpoint with the `genre` request parameter should return the number of books of the given genre.
  Example: the `/book?genre=sci-fi` get request should return the number of sci-fi books.
- A `get` request to the `/book/byWriter` endpoint with the `writersBirthPlace` request parameter should return a list of books with a writer from the specified country as JSON.
  Example: the `/book/byWriter?writersBirthPlace=UK` get request should return a list of books with a writer from the UK as JSON, like this:
```json 
[
    {
        "title": "Harry Potter and the Sorcerer's Stone",
        "writerName": "J.K. Rowling",
        "genre": "fantasy",
        "numberOwnedByLibrary": 10,
        "numberBorrowed": 7
    },
    {
        "title": "Harry Potter and the Chamber of Secrets",
        "writerName": "J.K. Rowling",
        "genre": "fantasy",
        "numberOwnedByLibrary": 12,
        "numberBorrowed": 12
    }
]
``` 
- A `get` request to an url like `/book/allBorrowed` should return a list of titles. A title should be on this list if all the copies owned by the library is borrowed.
  Example: the `/book/allBorrowed` get request should return this:
```json 
[
    "Harry Potter and the Chamber of Secrets",
    "Harry Potter and the Prisoner of Azkaban",
    "The Hitchhiker's Guide to the Galaxy"
]
```