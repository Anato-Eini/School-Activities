using Microsoft.AspNetCore.Mvc;
using ANI.DTO;
using ANI.Services;

namespace ANI.Controllers;

[Route("api/[controller]")]
public class ProductsController(IProductService productService) : ControllerBase
{
    private readonly IProductService _produceService = productService;

    // GET: api/ProductDTOs
    [HttpGet]
    public async Task<ActionResult<IEnumerable<ProductResponseDTO>>> GetProducts()
    {
        return Ok(await _produceService.GetProducts());
    }

    // GET: api/ProductDTOs/{id}
    [HttpGet("{id}")]
    public async Task<ActionResult<UserResponseDTO>> GetProduct(Guid id)
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

    // POST: api/ProductDTOs
    [HttpPost]
    public async Task<ActionResult<ProductResponseDTO>> PostProduct([FromForm] ProductCreateDTO productDTO)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        ProductResponseDTO createdProduct = await _produceService.CreateProduct(productDTO);

        return CreatedAtAction(nameof(GetProduct), new { id = createdProduct.ProductID }, createdProduct);
    }

    // PUT: api/ProductDTOs/5
    [HttpPut]
    public async Task<ActionResult<ProductResponseDTO>> PutProduct([FromBody] ProductUpdateDTO product)
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

    // DELETE: api/ProductResponseDTOs/5
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