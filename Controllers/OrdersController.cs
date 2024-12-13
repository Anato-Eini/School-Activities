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
    public async Task<ActionResult<IEnumerable<OrderResponseDTO>>> GetOrders(Guid userID) => Ok(await _orderService.GetOrders(userID));


    [HttpGet("farmer/{userID}")]
    public async Task<ActionResult<IEnumerable<OrderResponseDTO>>> GetOrdersToFarmer(Guid userID)
    {
        return Ok(await _orderService.GetOrders(userID));
    }

    
    [HttpGet("{orderID}")]
    public async Task<ActionResult<OrderResponseDTO>> GetOrder(Guid orderID)
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
    public async Task<ActionResult<OrderResponseDTO>> PostOrder([FromBody] OrderCreateDTO order)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        OrderResponseDTO createdOrder = await _orderService.CreateOrder(order);

        return CreatedAtAction(nameof(GetOrder), new { orderID = createdOrder.OrderID }, createdOrder);
    }

    
/*     [HttpPut]
    public async Task<ActionResult<OrderResponseDTO>> PutOrder([FromBody] OrderUpdateDTO order)
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
    } */

    
    [HttpDelete("{orderID}")]
    public async Task<ActionResult<OrderResponseDTO>> DeleteOrder(Guid orderID)
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
