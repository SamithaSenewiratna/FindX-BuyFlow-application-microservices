const mongoose  = require('mongoose');
const CatetegorySchema = new mongoose.Schema({
     
      categoryName :{ type: String, required :true },
      categoryName :{ type: Object },
      availableCountries:{type : Array}

});

module.exports = mongoose.model('catogery',CatetegorySchema);