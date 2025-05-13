const DiscountSchema = require('../model/DiscountSchema');

const createDiscount = async (request, response) => {
  try {
    const { discountName, percentage, startDate, endDate } = request.body;

    if (!discountName ||! percentage  || !startDate || !endDate ||!lastupdate ) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const discountData = new DiscountSchema({
      discountName,
      percentage,
      startDate,
      endDate,
      lastupdate
    });

    const result = await discountData.save();
    return response.status(201).json({ code: 201, message: 'Discount created successfully.', data: result });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const updateDiscount = async (request, response) => {
  try {
    const { discountName, percentage, startDate, endDate, lastupdate } = request.body;

    if (!discountName || percentage == null || !startDate || !endDate || !lastupdate) {
      return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
    }

    const updateData = await DiscountSchema.findByIdAndUpdate(
      request.params.id,
      {
        $set: {
          discountName,
          percentage,
          startDate,
          endDate,
          lastupdate
        }
      },
      { new: true }
    );

    if (!updateData) {
      return response.status(404).json({ code: 404, message: 'Discount not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Discount updated successfully.', data: updateData });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};


const deleteDiscount = async (request, response) => {
  try {
    const { id } = request.params;

    const deletedDiscount = await DiscountSchema.findByIdAndDelete(id);

    if (!deletedDiscount) {
      return response.status(404).json({ code: 404, message: 'Discount not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Discount deleted successfully.' });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findDiscountById = async (request, response) => {
  try {
    const discount = await DiscountSchema.findById(request.params.id);

    if (!discount) {
      return response.status(404).json({ code: 404, message: 'Discount not found.' });
    }

    return response.status(200).json({ code: 200, message: 'Discount fetched successfully.', data: discount });

  } catch (error) {
    return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

const findAllDiscounts = async (request, response) => {
  try {
    const { searchText, page = 1, size = 10 } = request.query;
    const pageIndex = parseInt(page);
    const pageSize = parseInt(size);

    const query = {};
    if (searchText) {
      query.$text = { $search: searchText };
    }

    const skip = (pageIndex - 1) * pageSize;
    const discountList = await DiscountSchema.find(query).limit(pageSize).skip(skip);
    const discountCount = await DiscountSchema.countDocuments(query);

    return response.status(200).json({ code: 200, message: 'Discounts fetched successfully.', data: { list: discountList, dataCount: discountCount } });

  } catch (error) {
       return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
};

module.exports = {
  createDiscount,
  updateDiscount,
  deleteDiscount,
  findDiscountById,
  findAllDiscounts
}
