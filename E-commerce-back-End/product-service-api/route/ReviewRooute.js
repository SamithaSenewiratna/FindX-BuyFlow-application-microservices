const express = require('express');
const ReviewController = require('../controller/ReviewController');

const router = express.Router();

router.post('/create-review',ReviewController.createReview);
router.put('/update-review/:id',ReviewController.updateReview);
router.delete('/delete-review/:id',ReviewController.deleteReview);
router.get('/findById-review/:id',ReviewController.findReviewById);
router.get('/findAll-review',ReviewController.findAllReviews);

module.exports = router;