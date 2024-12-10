using ANI.DTO;
using ANI.Services;
using Microsoft.AspNetCore.Mvc;

namespace ANI.Controllers;

[Route("api/[controller]")]
[ApiController]
public class OrdersController(IOrderService orderService) : ControllerBase
{
    private readonly IOrderService _orderService = orderService;

    [HttpGet]
    public async Task<ActionResult<IEnumerable<OrderDTO>>> GetOrders(Guid userID) => Ok(await _orderService.GetOrders(userID));

    
    [HttpGet("{orderID}")]
    public async Task<ActionResult<OrderDTO>> GetOrder(Guid orderID)
    {
        try
        {
            return Ok(await _orderService.GetOrder(orderID));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

   
    [HttpPost]
    public async Task<ActionResult<OrderDTO>> PostOrder([FromBody] OrderDTO order)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        OrderDTO createdOrder = await _orderService.CreateOrder(order);

        return CreatedAtAction(nameof(GetOrder), new { orderID = createdOrder.OrderID }, createdOrder);
    }

    
    [HttpPut]
    public async Task<ActionResult<OrderDTO>> PutOrder([FromBody] OrderDTO order)
    {
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        try
        {
            return Ok(await _orderService.UpdateOrder(order));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }

    
    [HttpDelete("{id}")]
    public async Task<ActionResult<OrderDTO>> DeleteOrder(Guid orderID)
    {
        try
        {
            return Ok(await _orderService.DeleteOrder(orderID));
        }
        catch (KeyNotFoundException e)
        {
            return NotFound(e.Message);
        }
    }
}
