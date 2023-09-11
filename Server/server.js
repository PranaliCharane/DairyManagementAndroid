const express = require("express")
const cors = require("cors")
const routerUser = require("./router/user")
const routerProductDetails = require("./router/ProductDetails")
const routerOrder = require("./router/orders")

const app = express()

app.use(express.json())
app.use(cors("*"))
app.use(express.static("uploads"))
app.use("/user", routerUser)
app.use("/ProductDetails", routerProductDetails)
app.use("/orders", routerOrder)

app.listen(4000, "0.0.0.0", () => {
  console.log("Server started at port 4000")
})
