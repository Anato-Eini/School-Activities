using ANI.DTO;

namespace ANI.Services;

/// <summary>
/// Interface for product service operations.
/// </summary>
public interface IProductService
{
    /// <summary>
    /// Retrieves a list of products.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable collection of <see cref="ProductResponseDTO"/>.</returns>
    Task<IEnumerable<ProductResponseDTO>> GetProducts();

    /// <summary>
    /// Retrieves a product by its identifier.
    /// </summary>
    /// <param name="productID">The unique identifier of the product.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the <see cref="ProductResponseDTO"/>.</returns>
    Task<ProductResponseDTO> GetProduct(Guid productID);

    /// <summary>
    /// Creates a new product.
    /// </summary>
    /// <param name="product">The product creation data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created <see cref="ProductResponseDTO"/>.</returns>
    Task<ProductResponseDTO> CreateProduct(ProductCreateDTO product);

    /// <summary>
    /// Updates an existing product.
    /// </summary>
    /// <param name="product">The product update data transfer object.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated <see cref="ProductResponseDTO"/>.</returns>
    Task<ProductResponseDTO> UpdateProduct(ProductUpdateDTO product);

    /// <summary>
    /// Deletes a product by its identifier.
    /// </summary>
    /// <param name="productID">The unique identifier of the product.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted <see cref="ProductResponseDTO"/>.</returns>
    Task<ProductResponseDTO> DeleteProduct(Guid productID);
}