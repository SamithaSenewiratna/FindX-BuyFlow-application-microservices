const express = require('express');
const CartController = require('../controller/CartController');

const router = express.Router();

router.post('/create-cart',CartController.createCart);
router.put('/update-cart/:id',CartController.updateCart);
router.delete('/delete-cart/:id',CartController.deleteCart);
router.get('/findById-cart/:id',CartController.findCartById);
router.get('/findAll-cart',CartController.findAllCarts);

module.exports = router;