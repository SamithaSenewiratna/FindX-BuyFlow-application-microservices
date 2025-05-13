 const express = require('express');
 const mongoose = require('mongoose');
 require('dotenv').config();
 const bodyParser = require('body-parser');

 const app = express();
 const serverPort = process.env.SEVER_PORT | 3000;
 app.use(bodyParser.json());

//---------------------------------------------------------
const CategoryRoute = require('./route/CategoryRoute');
const CountryRoute = require('./route/CountryRoute');
const DiscountRoute = require('./route/DiscountRoute');
const ProductRoute = require('./route/ProductRoute');
const CartRoute = require('./route/CartRoute');
const BookmarkRoute = require('./route/BookmarkRoute');
//---------------------------------------------------------

 try {
    mongoose.connect('mongodb://127.0.0.1:27017/products-DB')
        app.listen(serverPort,()=>{
            console.log(`Server up & running on port ${serverPort}`);
        });

 } catch (error) {
    console.log(error);
 }

 app.get('/test-api',(req,resp)=>{
    return resp.json({'message':'server is running '})
 });


 //----------------------------------------------
  app.use('/api/v1/categories',CategoryRoute);
  app.use('/api/v1/countries',CountryRoute);
  app.use('/api/v1/discounts',DiscountRoute);
  app.use('/api/v1/products',ProductRoute);
  app.use('/api/v1/carts',CartRoute);
  app.use('/api/v1/bookmarks',BookmarkRoute);
 //----------------------------------------------