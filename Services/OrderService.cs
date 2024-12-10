using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;

namespace ANI.Services;

public class OrderService(IOrderRepository orderRepository, IMapper mapper) : IOrderService
{
    private readonly IMapper _mapper = mapper;
    private readonly IOrderRepository _orderRepository = orderRepository;

    public async Task<IEnumerable<OrderDTO>> GetOrders(Guid userID)
    {
        return _mapper.Map<IEnumerable<OrderDTO>>(await _orderRepository.GetOrders(userID));
    }

    public async Task<OrderDTO> GetOrder(Guid orderID)
    {
        return _mapper.Map<OrderDTO>(await _orderRepository.GetOrder(orderID));
    }

    public async Task<OrderDTO> CreateOrder(OrderDTO order)
    {
        return _mapper.Map<OrderDTO>(await _orderRepository.CreateOrder(_mapper.Map<Order>(order)));
    }

    public async Task<OrderDTO> UpdateOrder(OrderDTO order)
    {
        return _mapper.Map<OrderDTO>(await _orderRepository.UpdateOrder(_mapper.Map<Order>(order)));
    }

    public async Task<OrderDTO> DeleteOrder(Guid orderID)
    {
        return _mapper.Map<OrderDTO>(await _orderRepository.DeleteOrder(orderID));
    }
}
