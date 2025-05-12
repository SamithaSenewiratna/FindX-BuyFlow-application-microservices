const express = require('express');
const CategoryController = require('../controller/CategoryController');

const router = express.Router();

router.post('/create-category',CategoryController.createCategory);
router.put('/update-category/:id',CategoryController.updateCategory);
router.delete('/delete-category/:id',CategoryController.deleteCategory);
router.get('/findById-category/:id',CategoryController.findCategoryById);
router.get('/findAll-category',CategoryController.findAllCategories);

module.exports = router;