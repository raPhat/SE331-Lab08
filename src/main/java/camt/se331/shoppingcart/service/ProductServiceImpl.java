package camt.se331.shoppingcart.service;

import camt.se331.shoppingcart.dao.ProductDao;
import camt.se331.shoppingcart.entity.Image;
import camt.se331.shoppingcart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Dto on 2/8/2015.
 */
@CrossOrigin
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }



    @Override
    public Product getProduct(Long id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = getProduct(id);
        return productDao.deleteProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDao.getProductsByName(name);
    }

    @Override
    @Transactional
    public Product addImage(Product product, Image image) {
        product.getImages().add(image);
        productDao.updateProduct(product);
        return product;
    }

    @Override
    @Transactional
    public Product removeImage(Product product, Long id) {
        //product.getImages().removeAll(product.getImages());
        Iterator<Image> imgitr = product.getImages().iterator();
        while (imgitr.hasNext()) {
            Image img = imgitr.next();
            //System.out.println("---- ID "+img.getId());
            if( img.getId().intValue() == id.intValue() ) {
                product.getImages().remove(img);
                //System.out.println("---- SIZE " + product.getImages().size());
            }
        }
        productDao.updateProduct(product);
        return product;
    }

}
