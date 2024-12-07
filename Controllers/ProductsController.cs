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
    public async Task<ActionResult<IEnumerable<ProductDTO>>> GetProducts()
    {
        return Ok(await _produceService.GetProducts());
    }

    // GET: api/ProductDTOs/5
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
    public async Task<ActionResult<ProductDTO>> PostProduct([FromBody] ProductDTO productDTO)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        ProductDTO createdProduct = await _produceService.CreateProduct(productDTO);

        return CreatedAtAction(nameof(GetProduct), new { id = createdProduct.ProductID }, createdProduct);
    }

    // PUT: api/ProductDTOs/5
    [HttpPut("{id}")]
    public async Task<ActionResult<ProductDTO>> PutProduct([FromBody] ProductDTO product)
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

    // DELETE: api/ProductDTOs/5
    [HttpDelete("{id}")]
    public async Task<ActionResult<ProductDTO>> DeleteProduct(Guid id)
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