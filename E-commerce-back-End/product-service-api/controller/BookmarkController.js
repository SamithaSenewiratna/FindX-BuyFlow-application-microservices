const BookmarkSchema = require('../model/BookmarkSchema');

const createBookmark = async (request, response) => {
  try {
    const { userId, productId, createdDate } = request.body;

    if (!userId || !productId || !createdDate) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const bookmarkData = new BookmarkSchema({
      userId,
      productId,
      createdDate
      
    });

    const result = await bookmarkData.save();

    return response.status(201).json({ code: 201, message: 'Bookmark added successfully.', data: result });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const updateBookmark = async (request, response) => {
  try {
    const { userId, productId, createdDate } = request.body;

    if (!userId || !productId || !createdDate) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const updateData = await BookmarkSchema.findByIdAndUpdate(
      request.params.id,
      {
        $set: {
          userId,
          productId,
          createdDate
        }
      },
      { new: true }
    );

    if (!updateData) {
      return response.status(404).json({ code: 404, message: 'Bookmark not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Bookmark updated successfully.', data: updateData });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const deleteBookmark = async (request, response) => {
  try {
    const { id } = request.params;

    if (!id) {
      return response.status(400).json({ code: 400, message: 'Bookmark ID is missing.' });
    }

    const deletedBookmark = await BookmarkSchema.findByIdAndDelete(id);

    if (deletedBookmark) {
      return response.status(200).json({ code: 200, message: 'Bookmark deleted successfully.' });
    }

    return response.status(404).json({ code: 404, message: 'Bookmark not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findBookmarkById = async (request, response) => {
  try {
    if (!request.params.id) {
      return response.status(400).json({ code: 400, message: 'Bookmark ID is missing.' });
    }

    const bookmarkData = await BookmarkSchema.findById(request.params.id);

    if (bookmarkData) {
      return response.status(200).json({ code: 200, message: 'Bookmark fetched successfully.', data: bookmarkData });
    }

    return response.status(404).json({ code: 404, message: 'Bookmark not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findAllBookmarks = async (request, response) => {
  try {
    const {  page = 1, size = 10 } = request.query;
    const pageIndex = parseInt(page);
    const pageSize = parseInt(size);

    const skip = (pageIndex - 1) * pageSize;
    const bookmarkList = await BookmarkSchema.find()
                         .limit(pageSize)
                         .skip(skip);
    const bookmarkCount = await BookmarkSchema.countDocuments();

    return response.status(200).json({  code: 200,  message: 'Bookmarks fetched successfully.', data: { list: bookmarkList, dataCount: bookmarkCount } });
  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

module.exports = {
  createBookmark,
  updateBookmark,
  deleteBookmark,
  findBookmarkById,
  findAllBookmarks
};
