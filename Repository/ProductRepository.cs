using ANI.Models;
using Microsoft.EntityFrameworkCore;


namespace ANI.Repository;

public class ProductRepository(AniContext context) : IProductRepository
{
    private readonly AniContext _context = context;

    public async Task<IEnumerable<Product>> GetProducts()
    {
        return await _context.Products.ToListAsync();
    }

    public async Task<Product> GetProduct(Guid productID)
    {
        return await _context.Products.FindAsync(productID) ?? throw new KeyNotFoundException($"Product with id {productID} not found.");
    }

    public async Task<Product> CreateProduct(Product product)
    {
        _context.Products.Add(product);

        await _context.SaveChangesAsync();

        return product;
    }

    public async Task<Product> UpdateProduct(Product product)
    {
        Product updateProduct = await _context.Products.FindAsync(product.ProductID) ?? throw new KeyNotFoundException($"Product with id {product.ProductID} not found.");

        updateProduct.Name = product.Name;
        updateProduct.Description = product.Description;
        updateProduct.Price = product.Price;
        updateProduct.Stock = product.Stock;

        _context.Entry(updateProduct).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return product;
    }

    public async Task<Product> DeleteProduct(Guid productID)
    {
        Product product = await _context.Products.FindAsync(productID) ?? throw new KeyNotFoundException($"Product with id {productID} not found.");

        _context.Products.Remove(product);
        await _context.SaveChangesAsync();

        return product;
    }
}