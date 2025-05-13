const express = require('express');
const ProductController = require('../controller/ProductController');

const router = express.Router();

router.post('/create-product',ProductController.createProduct);
router.put('/update-product/:id',ProductController.updateProduct);
router.delete('/delete-product/:id',ProductController.deleteProduct);
router.get('/findById-product/:id',ProductController.findProductById);
router.get('/findAll-product',ProductController.findAllProducts);

module.exports = router;