using ANI.Models;
using ANI.DTO;
using ANI.Repository;
using AutoMapper;
using ANI.Libraries;

namespace ANI.Services;

/// <summary>
/// Provides services for managing products, including retrieving, creating, updating, and deleting products.
/// </summary>
public class ProductService(IProductRepository productRepository, IMapper mapper, IUserRepository userRepository) : IProductService
{
    private readonly IMapper _mapper = mapper;
    private readonly IProductRepository _productRepository = productRepository;
    private readonly IUserRepository _userRepository = userRepository;

    /// <summary>
    /// Asynchronously retrieves a collection of products and maps them to ProductResponseDTO objects.
    /// </summary>
    /// <returns>
    /// A task that represents the asynchronous operation. The task result contains an IEnumerable of ProductResponseDTO objects.
    /// </returns>
    /// <remarks>
    /// This method fetches products from the repository, maps them to ProductResponseDTO objects, and updates the ProductPictureUrl property
    /// by prepending a URL using the Library.PrependUrl method.
    /// </remarks>
    public async Task<IEnumerable<ProductResponseDTO>> GetProducts()
    {
        return _mapper.Map<IEnumerable<ProductResponseDTO>>(await _productRepository.GetProducts()).Select(static product =>
        {
            product.ProductPictureUrl = Library.PrependUrl(product.ProductPictureUrl);
            return product;
        });
    }

    /// <summary>
    /// Asynchronously retrieves a product by its ID and maps it to a ProductResponseDTO object.
    /// </summary>
    /// <param name="productID">The unique identifier of the product to retrieve.</param>
    /// <returns>
    /// A task that represents the asynchronous operation. The task result contains a ProductResponseDTO object.
    /// </returns>
    /// <remarks>
    /// This method fetches a product from the repository by its ID, maps it to a ProductResponseDTO object, and updates the ProductPictureUrl property
    /// by prepending a URL using the Library.PrependUrl method.
    /// </remarks>
    public async Task<ProductResponseDTO> GetProduct(Guid productID)
    {
        ProductResponseDTO productDTO = _mapper.Map<ProductResponseDTO>(await _productRepository.GetProduct(productID));

        productDTO.ProductPictureUrl = Library.PrependUrl(productDTO.ProductPictureUrl);

        return productDTO;
    }

    /// <summary>
    /// Asynchronously creates a new product and maps it to a ProductResponseDTO object.
    /// </summary>
    /// <param name="product">The ProductCreateDTO object containing the details of the product to create.</param>
    /// <returns>
    /// A task that represents the asynchronous operation. The task result contains a ProductResponseDTO object.
    /// </returns>
    /// <remarks>
    /// This method maps the ProductCreateDTO to a Product entity, fetches the associated user from the repository, saves the product image,
    /// creates the product in the repository, and maps the created product to a ProductResponseDTO object. The ProductPictureUrl property
    /// is updated by prepending a URL using the Library.PrependUrl method.
    /// </remarks>
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


    /// <summary>
    /// Asynchronously updates an existing product and maps it to a ProductResponseDTO object.
    /// </summary>
    /// <param name="product">The ProductUpdateDTO object containing the updated details of the product.</param>
    /// <returns>
    /// A task that represents the asynchronous operation. The task result contains a ProductResponseDTO object.
    /// </returns>
    /// <remarks>
    /// This method fetches the original product from the repository by its ID, updates its properties with the values from the ProductUpdateDTO,
    /// saves the product image if a new one is provided, updates the product in the repository, and maps the updated product to a ProductResponseDTO object.
    /// The ProductPictureUrl property is updated by prepending a URL using the Library.PrependUrl method.
    /// </remarks>
    public async Task<ProductResponseDTO> UpdateProduct(ProductUpdateDTO product)
    {
        Product origProduct = await _productRepository.GetProduct(product.ProductID) ?? throw new KeyNotFoundException($"Product with id {product.ProductID} not found.");

        origProduct.Name = product.Name;
        origProduct.Price = product.Price;
        origProduct.Stock = product.Stock;

        if (product.Description != null)
            origProduct.Description = product.Description;

        if (product.ProductPictureUrl != null)
            origProduct.ProductPictureUrl = await Library.SaveImage("Products", product.ProductPictureUrl);

        ProductResponseDTO productResponseDTO = _mapper.Map<ProductResponseDTO>(await _productRepository.UpdateProduct(_mapper.Map<Product>(origProduct)));

        productResponseDTO.ProductPictureUrl = Library.PrependUrl(productResponseDTO.ProductPictureUrl);

        return productResponseDTO;
    }


    /// <summary>
    /// Asynchronously deletes a product by its ID and maps the result to a ProductResponseDTO object.
    /// </summary>
    /// <param name="productID">The unique identifier of the product to delete.</param>
    /// <returns>
    /// A task that represents the asynchronous operation. The task result contains a ProductResponseDTO object.
    /// </returns>
    /// <remarks>
    /// This method deletes a product from the repository by its ID and maps the result to a ProductResponseDTO object.
    /// </remarks>
    public async Task<ProductResponseDTO> DeleteProduct(Guid productID)
    {
        return _mapper.Map<ProductResponseDTO>(await _productRepository.DeleteProduct(productID));
    }
}