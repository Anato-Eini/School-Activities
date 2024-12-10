using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

/// <summary>
/// Repository class for managing products in the database.
/// </summary>
public class ProductRepository(AniContext context) : IProductRepository
{
    private readonly AniContext _context = context;

    /// <summary>
    /// Retrieves all products from the database.
    /// </summary>
    /// <returns>A list of products.</returns>
    public async Task<IEnumerable<Product>> GetProducts()
    {
        return await _context.Products.ToListAsync();
    }

    /// <summary>
    /// Retrieves a product by its ID.
    /// </summary>
    /// <param name="productID">The ID of the product to retrieve.</param>
    /// <returns>The product with the specified ID.</returns>
    /// <exception cref="KeyNotFoundException">Thrown when a product with the specified ID is not found.</exception>
    public async Task<Product> GetProduct(Guid productID)
    {
        return await _context.Products.FindAsync(productID) ?? throw new KeyNotFoundException($"Product with id {productID} not found.");
    }

    /// <summary>
    /// Creates a new product in the database.
    /// </summary>
    /// <param name="product">The product to create.</param>
    /// <returns>The created product.</returns>
    public async Task<Product> CreateProduct(Product product)
    {
        _context.Products.Add(product);

        await _context.SaveChangesAsync();

        return product;
    }

    /// <summary>
    /// Updates an existing product in the database.
    /// </summary>
    /// <param name="product">The product to update.</param>
    /// <returns>The updated product.</returns>
    public async Task<Product> UpdateProduct(Product product)
    {
        _context.Entry(product).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return product;
    }

    /// <summary>
    /// Deletes a product by its ID.
    /// </summary>
    /// <param name="productID">The ID of the product to delete.</param>
    /// <returns>The deleted product.</returns>
    public async Task<Product> DeleteProduct(Guid productID)
    {
        Product product = await _context.Products.FindAsync(productID) ?? throw new KeyNotFoundException($"Product with id {productID} not found.");

        _context.Products.Remove(product);
        await _context.SaveChangesAsync();

        return product;
    }
}