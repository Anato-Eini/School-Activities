namespace ANI.DTO;

public class OrderDTO
{
    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
}

