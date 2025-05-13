const CategorySchema = require('../model/CategorySchema');

const createCategory = async (request, response) => {
    try {
        const { categoryName, file, countryIds } = request.body;

        if (!categoryName || !file || !countryIds) {
            return response.status(400).json({ code: 400, message: 'Some fields are missing..' });
        }

        const iconData = {
            hash: 'temphash',
            resourceUrl: 'https://your-s3-bucket-url/image.jpg',
            fileName: 'your-file.jpg',
            directory: 'categories/icons'
        };

        const availableCountries = countryIds.map(c => ({
            countryId: c.countryId,
            countryName: c.countryName
        }));

        const categoryData = new CategorySchema({
            categoryName,
            icon: iconData,
            availableCountries
        });

        const result = await categoryData.save();

     return response.status(201).json({ code: 201, message: 'Category saved successfully.', data: result });

    } catch (error) {
        return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message});
    }
};


const updateCategory = async (request, response) => {
  try {
    const { categoryName } = request.body;
       if (!categoryName) {
             return response.status(400).json({code: 400, message: 'Some fields are missing..', data: null });
        }

        const updateData = await CategorySchema.findOneAndUpdate({ id: request.params.id },
            {
                $set: {
                categoryName: categoryName
                }
            },
            { new: true }
        );

     return response.status(200).json({ code: 200, message: 'Category has been updated.....', data: updateData});

    }catch (error) {
        return response.status(500).json({code: 500, message: 'Something went wrong.', error: error.message });
    }

};


const deleteCategory = async (request, response) => {
  try {
    const { id } = request.params;

        if (!id) {
        return response.status(400).json({ code: 400, message: 'Some fields are missing.' });
        }

        const deletedCategory = await CategorySchema.findByIdAndDelete(id);

        if (deletedCategory) {
        return response.status(200).json({ code: 200, message: 'Category deleted successfully.' });
        }

      return response.status(404).json({ code: 404, message: 'Category not found.' });

    } catch (error) {
        return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
    }
}

const findCategoryById = async (request,response)=>{
    try {
        if (!request.params.id) {
            return response.status(400).json({ code: 400, message: 'Some feilds are missimg..' });
        }

        const categoryData = await CategorySchema.findById(request.params.id); 
            if(categoryData){
                return response.status(200).json({code:200,message:'category data...',data:categoryData})
            }
        
        return response.status(404).json({code:404,message:'category data not found...',data:response})
  
        } catch (error) {
            return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
        }
}

const findAllCategories = async (request,response)=>{
  try {
        const categories = await CategorySchema.find();
        return response.status(200).json({ code: 200, message: 'Categories fetched successfully.', data: categories });
  } catch (error) {
        return response.status(500).json({ code: 500, message: 'Something went wrong.', error: error.message });
  }
}

module.exports = {
   createCategory,
   updateCategory,
   deleteCategory,
   findCategoryById,
   findAllCategories
}