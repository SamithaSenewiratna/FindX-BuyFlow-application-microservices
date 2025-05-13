const ProductSchema = require('../model/ProductSchema');

const createProduct = async (request, response) => {
  try {
    const { productName, images, actualPrice, oldPrice, qty, discription, discount, categoryId } = request.body;

    if (!productName || !images || !actualPrice || !qty || !categoryId) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const productData = new ProductSchema({
      productName,
      images,
      actualPrice,
      oldPrice,
      qty,
      discription,
      discount,
      categoryId
    });

    const result = await productData.save();

    return response.status(201).json({ code: 201, message: 'Product saved successfully.', data: result });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const updateProduct = async (request, response) => {
  try {
    const { productName, images, actualPrice, oldPrice, qty, discription, discount, categoryId } = request.body;

    if (!productName || !images || !actualPrice || !qty || !categoryId) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const updateData = await ProductSchema.findByIdAndUpdate(
      request.params.id,
      {
        $set: {
          productName,
          images,
          actualPrice,
          oldPrice,
          qty,
          discription,
          discount,
          categoryId
        }
      },
      { new: true }
    );

    if (!updateData) {
      return response.status(404).json({ code: 404, message: 'Product not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Product updated successfully.', data: updateData });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const deleteProduct = async (request, response) => {
  try {
    const { id } = request.params;

    if (!id) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const deletedProduct = await ProductSchema.findByIdAndDelete(id);

    if (deletedProduct) {
      return response.status(200).json({ code: 200, message: 'Product deleted successfully.' });
    }

    return response.status(404).json({ code: 404, message: 'Product not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findProductById = async (request, response) => {
  try {
    if (!request.params.id) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const productData = await ProductSchema.findById(request.params.id);

    if (productData) {
      return response.status(200).json({ code: 200, message: 'Product data fetched.', data: productData });
    }

    return response.status(404).json({ code: 404, message: 'Product not found.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findAllProducts = async (request, response) => {
  try {
    const { searchText, page = 1, size = 10 } = request.query;
    const pageIndex = parseInt(page);
    const pageSize = parseInt(size);

    const query = {};
    if (searchText) {
      query.$text = { $search: searchText };
    }

    const skip = (pageIndex - 1) * pageSize;
    const productList = await ProductSchema.find(query)
        .limit(pageSize)
        .skip(skip);
    const productCount = await ProductSchema.countDocuments(query);

    return response.status(200).json({  code: 200, message: 'Products fetched successfully.', data: { list: productList, dataCount: productCount } });
  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

module.exports = {
  createProduct,
  updateProduct,
  deleteProduct,
  findProductById,
  findAllProducts
};
