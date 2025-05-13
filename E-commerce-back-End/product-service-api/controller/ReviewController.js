const ReviewSchema = require('../model/ReviewSchema');

const createReview = async (request, response) => {
  try {
    const { orderId, message, createdDate, userId, qty, displayName, productId, ratings } = request.body;

    if (!orderId || !message || !createdDate ||! userId  || !qty || !displayName || !productId ||! ratings) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const reviewData = new ReviewSchema({
      orderId,
      message,
      createdDate,
      userId,
      qty,
      displayName,
      productId,
      ratings
    });

    const result = await reviewData.save();

    return response.status(201).json({ code: 201, message: 'Review created successfully.', data: result });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const updateReview = async (request, response) => {
  try {
    const { orderId, message, createdDate, userId, qty, displayName, productId, ratings } = request.body;

    if (!orderId || !message || !createdDate || !userId  || !qty || !displayName || !productId || !ratings ) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const updateData = await ReviewSchema.findByIdAndUpdate(
      request.params.id,
      {
        $set: {
          orderId,
          message,
          createdDate,
          userId,
          qty,
          displayName,
          productId,
          ratings
        }
      },
      { new: true }
    );

    if (!updateData) {
      return response.status(404).json({ code: 404, message: 'Review not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Review updated successfully.', data: updateData });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const deleteReview = async (request, response) => {
  try {
    const { id } = request.params;

    if (!id) {
      return response.status(400).json({ code: 400, message: 'Review ID is missing.' });
    }

    const deletedReview = await ReviewSchema.findByIdAndDelete(id);

    if (deletedReview) {
      return response.status(200).json({ code: 200, message: 'Review deleted successfully.' });
    }

    return response.status(404).json({ code: 404, message: 'Review not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findReviewById = async (request, response) => {
  try {
    const { id } = request.params;

    if (!id) {
      return response.status(400).json({ code: 400, message: 'Review ID is missing.' });
    }

    const reviewData = await ReviewSchema.findById(id);

    if (reviewData) {
      return response.status(200).json({ code: 200, message: 'Review fetched successfully.', data: reviewData });
    }

    return response.status(404).json({ code: 404, message: 'Review not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findAllReviews = async (request, response) => {
  try {
    const {  page = 1, size = 10 } = request.query;
    const pageIndex = parseInt(page);
    const pageSize = parseInt(size);

    const skip = (pageIndex - 1) * pageSize;
    const reviewList = await ReviewSchema.find()
                        .limit(pageSize)
                        .skip(skip);
    const reviewCount = await ReviewSchema.countDocuments();

    return response.status(200).json({  code: 200,  message: 'Reviews fetched successfully.', data: { list: reviewList, dataCount: reviewCount } });
  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

module.exports = {
  createReview,
  updateReview,
  deleteReview,
  findReviewById,
  findAllReviews
};
