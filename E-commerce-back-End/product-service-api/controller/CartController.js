const CartSchema = require('../model/CartSchema');

const createCart = async (request, response) => {
  try {
    const { userId, productId, createdDate } = request.body;

    if (!userId || !productId || !createdDate ||!  qty) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const cartData = new CartSchema({
      userId,
      productId,
      qty,
      createdDate
    });

    const result = await cartData.save();

    return response.status(201).json({ code: 201, message: 'Cart item added successfully.', data: result });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const updateCart = async (request, response) => {
  try {
    const { userId, productId, createdDate } = request.body;

    if (!userId || !productId || !createdDate||!  qty) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const updateData = await CartSchema.findByIdAndUpdate(
      request.params.id,
      {
        $set: {
          userId,
          productId,
          qty,
          createdDate
        }
      },
      { new: true }
    );

    if (!updateData) {
      return response.status(404).json({ code: 404, message: 'Cart item not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Cart item updated successfully.', data: updateData });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const deleteCart = async (request, response) => {
  try {
    const { id } = request.params;

    if (!id) {
      return response.status(400).json({ code: 400, message: 'Cart ID is missing.' });
    }

    const deletedCart = await CartSchema.findByIdAndDelete(id);

    if (deletedCart) {
      return response.status(200).json({ code: 200, message: 'Cart item deleted successfully.' });
    }

    return response.status(404).json({ code: 404, message: 'Cart item not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findCartById = async (request, response) => {
  try {
    if (!request.params.id) {
      return response.status(400).json({ code: 400, message: 'Cart ID is missing.' });
    }

    const cartData = await CartSchema.findById(request.params.id);

    if (cartData) {
      return response.status(200).json({ code: 200, message: 'Cart item fetched successfully.', data: cartData });
    }

    return response.status(404).json({ code: 404, message: 'Cart item not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findAllCarts = async (request, response) => {
  try {
    const {  page = 1, size = 10 } = request.query;
    const pageIndex = parseInt(page);
    const pageSize = parseInt(size);

    const skip = (pageIndex - 1) * pageSize;
    const cartList = await CartSchema.find()
                   .limit(pageSize)
                   .skip(skip);
    const cartCount = await CartSchema.countDocuments();

    return response.status(200).json({ code: 200, message: 'Cart items fetched successfully.',  data: { list: cartList, dataCount: cartCount } });
  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

module.exports = {
  createCart,
  updateCart,
  deleteCart,
  findCartById,
  findAllCarts

};
