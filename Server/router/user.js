const express = require("express")
const db = require("../db")
const utils = require("../utils")

const router = express.Router()

router.post("/register", (request, response) => {
  const { id,uname, email, password,mobile } = request.body
  db.query(
    "INSERT INTO users(id,uname,email,password,mobile) VALUES(?,?,?,?,?)",
    [id,uname, email, password,mobile],
    (error, result) => {
      response.send(utils.createResult(error, result))
    }
  )
})

router.post("/login", (request, response) => {
  const { email, password } = request.body
  const statement = "SELECT * FROM users WHERE email=? and password=?"
  db.query(statement, [email, password], (error, result) => {
    response.send(utils.createResult(error, result))
  })
})

router.get("/:id", (request, response) => {
  const id = request.params.id
  const statement = `SELECT * FROM users WHERE id=?`
  db.query(statement, [id], (error, result) => {
    response.send(utils.createResult(error, result))
  })
})

module.exports = router
