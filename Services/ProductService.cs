using ANI.Models;
using ANI.DTO;
using ANI.Repository;
using AutoMapper;

namespace ANI.Services;

public class ProductService(IProductRepository productRepository, IMapper mapper) : IProductService
{
    private readonly IMapper _mapper = mapper;
    private readonly IProductRepository _productRepository = productRepository;

    public async Task<IEnumerable<ProductDTO>> GetProducts()
    {
        return _mapper.Map<IEnumerable<ProductDTO>>(await _productRepository.GetProducts());

    }

    public async Task<ProductDTO> GetProduct(Guid productID)
    {
        return _mapper.Map<ProductDTO>(await _productRepository.GetProduct(productID)); 
    }

    public async Task<ProductDTO> CreateProduct(ProductDTO product)
    {
        return _mapper.Map<ProductDTO>(await _productRepository.CreateProduct(_mapper.Map<Product>(product)));
    }

    public async Task<ProductDTO> UpdateProduct(ProductDTO product)
    {
        return _mapper.Map<ProductDTO>(await _productRepository.UpdateProduct(_mapper.Map<Product>(product)));
    }

    public async Task<ProductDTO> DeleteProduct(Guid productID)
    {
        Product product = await _productRepository.DeleteProduct(productID);

        return new ProductDTO
        {
            ProductID = product.ProductID,
            Name = product.Name,
            Description = product.Description,
            Price = product.Price,
            Stock = product.Stock
        };
    }
}
