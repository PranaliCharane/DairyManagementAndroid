const mysql = require("mysql")

const connection = mysql.createConnection({
  user: "root",
  password: "manager",
  host: "localhost",
  port: 3306,
  database: "Project",
})

connection.connect()

module.exports = connection
