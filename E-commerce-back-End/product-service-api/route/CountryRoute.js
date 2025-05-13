const express = require('express');
const CountryController = require('../controller/CountryController');

const router = express.Router();

router.post('/create-country',CountryController.createCountry);
router.put('/update-country/:id',CountryController.updateCountry);
router.delete('/delete-country/:id',CountryController.deleteCountry);
router.get('/findById-country/:id',CountryController.findCountryById);
router.get('/findAll-country',CountryController.findAllCountries);

module.exports = router;