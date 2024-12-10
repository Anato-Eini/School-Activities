using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace ANI.Controllers;

/// <summary>
/// Controller for managing products.
/// </summary>
/// <remarks>
/// Initializes a new instance of the <see cref="ProductsController"/> class.
/// </remarks>
/// <param name="productService">The product service.</param>
[ApiController]
[Route("api/[controller]")]
public class ProductsController(IProductService productService) : ControllerBase
{
    private readonly IProductService _produceService = productService;

    /// <summary>
    /// Gets the list of products.
    /// </summary>
    /// <returns>A list of <see cref="ProductResponseDTO"/>.</returns>
    [HttpGet]
    public async Task<ActionResult<IEnumerable<ProductResponseDTO>>> GetProducts()
    {
        return Ok(await _produceService.GetProducts());
    }

    /// <summary>
    /// Gets a product by its identifier.
    /// </summary>
    /// <param name="id">The product identifier.</param>
    /// <returns>A <see cref="ProductResponseDTO"/>.</returns>
    [HttpGet("{id}")]
    public async Task<ActionResult<ProductResponseDTO>> GetProduct(Guid id)
    {
        try
        {
            return Ok(await _produceService.GetProduct(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    /// <summary>
    /// Creates a new product.
    /// </summary>
    /// <param name="productDTO">The product creation data transfer object.</param>
    /// <returns>The created <see cref="ProductResponseDTO"/>.</returns>
    [HttpPost]
    public async Task<ActionResult<ProductResponseDTO>> PostProduct([FromForm] ProductCreateDTO productDTO)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        ProductResponseDTO createdProduct = await _produceService.CreateProduct(productDTO);

        return CreatedAtAction(nameof(GetProduct), new { id = createdProduct.ProductID }, createdProduct);
    }

    /// <summary>
    /// Updates an existing product.
    /// </summary>
    /// <param name="product">The product update data transfer object.</param>
    /// <returns>The updated <see cref="ProductResponseDTO"/>.</returns>
    [HttpPut]
    public async Task<ActionResult<ProductResponseDTO>> PutProduct([FromForm] ProductUpdateDTO product)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        try
        {
            return Ok(await _produceService.UpdateProduct(product));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    /// <summary>
    /// Deletes a product by its identifier.
    /// </summary>
    /// <param name="id">The product identifier.</param>
    /// <returns>The deleted <see cref="ProductResponseDTO"/>.</returns>
    [HttpDelete("{id}")]
    public async Task<ActionResult<ProductResponseDTO>> DeleteProduct(Guid id)
    {
        try
        {
            return Ok(await _produceService.DeleteProduct(id));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }
}