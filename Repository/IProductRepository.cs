using ANI.Models;

namespace ANI.Repository;

/// <summary>
/// Interface for product repository to handle CRUD operations for products.
/// </summary>
public interface IProductRepository
{
    /// <summary>
    /// Gets all products.
    /// </summary>
    /// <returns>A task that represents the asynchronous operation. The task result contains an enumerable of products.</returns>
    Task<IEnumerable<Product>> GetProducts();

    /// <summary>
    /// Gets a product by its ID.
    /// </summary>
    /// <param name="productID">The ID of the product.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the product.</returns>
    Task<Product> GetProduct(Guid productID);

    /// <summary>
    /// Creates a new product.
    /// </summary>
    /// <param name="product">The product to create.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the created product.</returns>
    Task<Product> CreateProduct(Product product);

    /// <summary>
    /// Updates an existing product.
    /// </summary>
    /// <param name="product">The product to update.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the updated product.</returns>
    Task<Product> UpdateProduct(Product product);

    /// <summary>
    /// Deletes a product by its ID.
    /// </summary>
    /// <param name="productID">The ID of the product to delete.</param>
    /// <returns>A task that represents the asynchronous operation. The task result contains the deleted product.</returns>
    Task<Product> DeleteProduct(Guid productID);
}