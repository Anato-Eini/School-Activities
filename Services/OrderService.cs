using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;

namespace ANI.Services;

public class OrderService(IOrderRepository orderRepository, IMapper mapper, IProductRepository productRepository) : IOrderService
{
    private readonly IMapper _mapper = mapper;
    private readonly IOrderRepository _orderRepository = orderRepository;
    private readonly IProductRepository _productRepository = productRepository;

    public async Task<IEnumerable<OrderResponseDTO>> GetOrders(Guid userID)
    {
        return _mapper.Map<IEnumerable<OrderResponseDTO>>(await _orderRepository.GetOrders(userID));
    }

    public async Task<OrderResponseDTO> GetOrder(Guid orderID)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.GetOrder(orderID));
    }

    public async Task<IEnumerable<OrderResponseDTO>> GetOrdersToFarmer(Guid userID) => _mapper.Map<IEnumerable<OrderResponseDTO>>(await _orderRepository.GetOrdersToFarmer(userID));
    
    public async Task<OrderResponseDTO> CreateOrder(OrderCreateDTO order)
    {
        Product product = await _productRepository.GetProduct(order.ProductID);

        if (product.Stock < order.Quantity)
            throw new InvalidOperationException("Not enough stock");

        product.Stock -= order.Quantity;
        await _productRepository.UpdateProduct(product);

        return _mapper.Map<OrderResponseDTO>(await _orderRepository.CreateOrder(_mapper.Map<Order>(order)));
    }

    public async Task<OrderResponseDTO> UpdateOrder(OrderUpdateDTO order)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.UpdateOrder(_mapper.Map<Order>(order)));
    }

    public async Task<OrderResponseDTO> DeleteOrder(Guid orderID)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.DeleteOrder(orderID));
    }
}
