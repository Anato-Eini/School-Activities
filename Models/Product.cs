using System.ComponentModel.DataAnnotations.Schema;

namespace ANI.Models;

public class Product
{
    public int ProductID { get; set; }
    public string Name { get; set; } = null!;
    public string Description { get; set; } = null!;
    public decimal Price { get; set; }
    public int Stock { get; set; }
    public int UserID { get; set; }

    [ForeignKey("UserID")]
    public virtual User User { get; set; } = null!;
}