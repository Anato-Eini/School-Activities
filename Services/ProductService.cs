using ANI.Models;
using ANI.DTO;
using ANI.Repository;
using AutoMapper;
using ANI.Libraries;

namespace ANI.Services;

public class ProductService(IProductRepository productRepository, IMapper mapper, IUserRepository userRepository) : IProductService
{
    private readonly IMapper _mapper = mapper;
    private readonly IProductRepository _productRepository = productRepository;
    private readonly IUserRepository _userRepository = userRepository;

    public async Task<IEnumerable<ProductResponseDTO>> GetProducts()
    {
        return _mapper.Map<IEnumerable<ProductResponseDTO>>(await _productRepository.GetProducts()).Select(product =>
        {
            product.ProductPictureUrl = Library.PrependUrl(product.ProductPictureUrl);
            return product;
        });
    }

    public async Task<ProductResponseDTO> GetProduct(Guid productID)
    {
        ProductResponseDTO productDTO = _mapper.Map<ProductResponseDTO>(await _productRepository.GetProduct(productID));

        productDTO.ProductPictureUrl = Library.PrependUrl(productDTO.ProductPictureUrl);

        return productDTO;
    }

    public async Task<ProductResponseDTO> CreateProduct(ProductCreateDTO product)
    {
        Product productCreate = _mapper.Map<Product>(product);

        productCreate.User = await _userRepository.GetUser(product.UserID) ?? throw new KeyNotFoundException($"User with id {product.UserID} not found.");
        productCreate.UserID = product.UserID;
        productCreate.ProductPictureUrl = await Library.SaveImage("Products", product.ProductPictureUrl);
        
        ProductResponseDTO productResponseDTO = _mapper.Map<ProductResponseDTO>(await _productRepository.CreateProduct(_mapper.Map<Product>(productCreate)));
        productResponseDTO.ProductPictureUrl = Library.PrependUrl(productResponseDTO.ProductPictureUrl);

        return productResponseDTO;
    }

    public async Task<ProductResponseDTO> UpdateProduct(ProductUpdateDTO product)
    {
        Product origProduct = await _productRepository.GetProduct(product.ProductID) ?? throw new KeyNotFoundException($"Product with id {product.ProductID} not found.");

        product.Name = product.Name;
        product.Description = product.Description;
        product.Price = product.Price;
        product.Stock = product.Stock;

        if (product.ProductPictureUrl != null)
            origProduct.ProductPictureUrl = await Library.SaveImage("Products", product.ProductPictureUrl);
        
        return _mapper.Map<ProductResponseDTO>(await _productRepository.UpdateProduct(_mapper.Map<Product>(product)));
    }

    public async Task<ProductResponseDTO> DeleteProduct(Guid productID)
    {
        return _mapper.Map<ProductResponseDTO>(await _productRepository.DeleteProduct(productID));
    }
}
