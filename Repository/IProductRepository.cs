using ANI.Models;

namespace ANI.Repository;

public interface IProductRepository
{
    public Task<IEnumerable<Product>> GetProducts();
    public Task<Product> GetProduct(Guid productID);
    public Task<Product> CreateProduct(Product product);
    public Task<Product> UpdateProduct(Product product);
    public Task<Product> DeleteProduct(Guid productID);
}