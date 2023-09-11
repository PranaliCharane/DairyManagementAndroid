CREATE DATABASE Project;
USE Project;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ProductDetails;
DROP TABLE IF EXISTS orders;


CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    uname VARCHAR(20),
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(20),
    mobile VARCHAR(15)
);

CREATE TABLE ProductDetails (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    description LONGTEXT,
    company VARCHAR(255),
    image VARCHAR(255)
);

CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    uid INT,
    mid INT,
    FOREIGN KEY (uid) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (mid) REFERENCES ProductDetails(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO ProductDetails (id,name, price, description,company,image) 
VALUES('1','low fat milk', 19.99, 'The system prioritizes low-fat dairy products, allowing users to quickly identify and manage inventory levels for these healthier options','Amul','low fat milk.jpg');
('cow milk', 49.99, 'the calcium / phosphorus ratio is higher in cow milk (2.26) than cow milk (1.96). cow milk has low heat stability in concentrated form (2:1).'),
('buffalo milk', 9.95, 'Buffalo milk contains more calcium and phosphorus (0.22% and 0.13% respectively) than cow milk (0.12% and 0.09% respectively)'),
('curd', 199.00, 'Curd, also known as yogurt, is a popular dairy product derived from the fermentation of milk'),
('panner', 29.99, 'Paneer is an Indian cheese made by curdling milk using heat and acid'),
('butter milk', 39.99, 'Buttermilk is a popular dairy product that is obtained as a byproduct of the butter-churning process'),
('cheese', 14.50, 'Cheese is a popular and versatile dairy product derived from milk through a complex process of coagulation, fermentation, draining, and aging. I'),
('lassi', 7.99, 'Lassi is a traditional and popular Indian yogurt-based drink known for its refreshing taste and creamy texture'),
('khoya', 79.99, 'Among the indigenous milk products, khoa occupies a prominent place as it forms a base for number of sweet delicacies');




INSERT INTO users (id,uname, email, password,mobile) 
VALUES('1','harshada', 'harshada@gmail.com', 'harsha', '1234567781');
('divya', 'divya@example.com', '9876543210', 'satara'),
('shraddha', 'shraddha@example.com', '1234567890', 'nashik'),
('pranali', 'pranali@example.com', '1234567890', 'kolhapur'),
('abc', 'abc@example.com', '1234567890', 'uk'),
('ketan', 'ketan@example.com', '1234567890', 'amalner),
('pqr', 'pqr@example.com', '1234567890', 'dhule'),
('lmn', 'lmn@example.com', '1234567890', 'jalgaon'),
('xyz', 'xyz@example.com', '1234567890', 'bhusawal'),
('john', 'john@example.com', '1234567890', 'nagpur');
