const express = require("express")
const db = require("../db")
const router = express.Router()
const utils = require("../utils")

router.post("/placeorder", (request, response) => {
  const { uid, mid } = request.body
  const statement = `INSERT INTO orders(uid,mid) VALUES(?,?)`
  db.query(statement, [uid, mid], (error, result) => {
    response.send(utils.createResult(error, result))
  })
})

router.get("/:id", (request, response) => {
  const id = request.params.id
  const statement = `SELECT m.* FROM ProductDetails m INNER JOIN orders o on m.id=o.mid WHERE o.uid=?`
  db.query(statement, [id], (error, result) => {
    response.send(utils.createResult(error, result))
  })
})

module.exports = router
