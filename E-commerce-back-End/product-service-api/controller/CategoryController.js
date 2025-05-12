const CategorySchema = require('../model/CategorySchema');

const createCategory = async (request,response)=>{
    console.log(request.body);
}

const updateCategory = async (request,response)=>{
    console.log(request.body);
}

const deleteCategory = async (request,response)=>{
    console.log(request.body);
}

const findCategoryById = async (request,response)=>{
    console.log(request.body);
}

const findAllCategories = async (request,response)=>{
    console.log(request.body);
}

module.exports = {
   createCategory,updateCategory,deleteCategory,findCategoryById,findAllCategories
}