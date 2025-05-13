const express = require('express');
const BookmarkController = require('../controller/BookmarkController');

const router = express.Router();

router.post('/create-bookmark',BookmarkController.createBookmark);
router.put('/update-bookmark/:id',BookmarkController.updateBookmark);
router.delete('/delete-bookmark/:id',BookmarkController.deleteBookmark);
router.get('/findById-bookmark/:id',BookmarkController.findBookmarkById);
router.get('/findAll-bookmark',BookmarkController.findAllBookmarks);

module.exports = router;