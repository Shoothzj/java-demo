schema.sql
```sql
DROP TABLE IF EXISTS student;
CREATE TABLE student (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
```
data.sql
```sql
INSERT INTO student(name, email) VALUES ('tom', 'tom@yahoo.com')
```