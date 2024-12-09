using ANI.DTO;

namespace ANI.Services;

public interface IProductService
{
    Task<IEnumerable<ProductResponseDTO>> GetProducts();
    Task<ProductResponseDTO> GetProduct(Guid productID);
    Task<ProductResponseDTO> CreateProduct(ProductResponseDTO product);
    Task<ProductResponseDTO> UpdateProduct(ProductUpdateDTO product);
    Task<ProductResponseDTO> DeleteProduct(Guid productID);
}