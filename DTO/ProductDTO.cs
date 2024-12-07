namespace ANI.DTO;

public class ProductDTO
{
    public Guid ProductID { get; set; }
    public string Name { get; set; } = null!;
    public string Description { get; set; } = null!;
    public decimal Price { get; set; }
    public int Stock { get; set; }
    public int UserID { get; set; }
}