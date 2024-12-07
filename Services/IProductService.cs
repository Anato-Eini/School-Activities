using ANI.DTO;

namespace ANI.Services;

public interface IProductService
{
    Task<IEnumerable<ProductDTO>> GetProducts();
    Task<ProductDTO> GetProduct(Guid productID);
    Task<ProductDTO> CreateProduct(ProductDTO product);
    Task<ProductDTO> UpdateProduct(ProductDTO product);
    Task<ProductDTO> DeleteProduct(Guid productID);
}