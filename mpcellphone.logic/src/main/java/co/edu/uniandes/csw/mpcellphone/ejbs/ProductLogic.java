package co.edu.uniandes.csw.mpcellphone.ejbs;

import co.edu.uniandes.csw.mpcellphone.api.IProductLogic;
import co.edu.uniandes.csw.mpcellphone.converters.ProductConverter;
import co.edu.uniandes.csw.mpcellphone.dtos.ProductDTO;
import co.edu.uniandes.csw.mpcellphone.entities.ProductEntity;
import co.edu.uniandes.csw.mpcellphone.persistence.ProductPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ProductLogic implements IProductLogic {

    @Inject private ProductPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countProducts() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ProductDTO> getProducts(Integer page, Integer maxRecords) {
        return ProductConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    @Override
    public ProductDTO getProduct(Long id) {
        return ProductConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        ProductEntity entity = ProductConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return ProductConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public ProductDTO updateProduct(ProductDTO dto) {
        ProductEntity entity = persistence.update(ProductConverter.fullDTO2Entity(dto));
        return ProductConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteProduct(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<ProductDTO> findByName(String name) {
        return ProductConverter.listEntity2DTO(persistence.findByName(name));
    }
    
    @Override
    public List<ProductDTO> getByCellPhoneName(String name){
        return ProductConverter.listEntity2DTO(persistence.getByCellPhoneName(name));
    }
    
    @Override
    public ProductDTO getCheaperProduct(Long idProvider){       
        return ProductConverter.fullEntity2DTO(persistence.getCheaperProduct(idProvider));
    }
    
    @Override
    public ProductDTO getCheaperProvider(Long idCellPhone){       
        return ProductConverter.fullEntity2DTO(persistence.getCheaperProvider(idCellPhone));
    }
    //Para Obtener la lista de un modelo especifico, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByModel(String model) {
        return ProductConverter.listEntity2DTO(persistence.getByModel(model));
    }
    //Para Obtener la lista de un modelo especifico filtrado por marca, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByBrand(String brand) {
        return ProductConverter.listEntity2DTO(persistence.getByBrand(brand));
    }
    
    //Para Obtener la lista de un modelo especifico de un proveedor, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByProviderName(String name) {
        return ProductConverter.listEntity2DTO(persistence.getByProviderName(name));
    }
    //Para Obtener la lista de un modelo especifico filtrado por ciudad, Desarrollado por Miguel Olivares
    public List<ProductDTO> getByCity(String city) {
        return ProductConverter.listEntity2DTO(persistence.getByCity(city));
    }
    //Para Obtener la lista de un modelo especifico filtrado por ciudad, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByPriceRange(Integer minPrice, Integer maxPrice) {
        return ProductConverter.listEntity2DTO(persistence.getByPriceRange(minPrice, maxPrice));
    }
    //Para Obtener la lista de un modelo especifico filtrado por descuento, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByDiscount(){
        return ProductConverter.listEntity2DTO(persistence.getByDiscount());
    }
    //Para Obtener la lista de un modelo especifico filtrado por ciudad, Desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getByCategory(String category) {
        return ProductConverter.listEntity2DTO(persistence.getByCategory(category));
    }
    
    //Para Obtener la lista de categorias, desarrollado por Miguel Olivares
    @Override
    public List<ProductDTO> getCategories(){
        return ProductConverter.listString2DTO(persistence.getCategories());
        
    }
}
