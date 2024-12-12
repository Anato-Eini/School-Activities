using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ANI.Models;
public class Order
{
    [Key]
    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
    public Guid UserID { get; set; }
    public Guid ProductID { get; set; }
    public DateTime CreatedAt { get; set; }
    [ForeignKey("UserID")]
    public virtual User User { get; set; } = null!;
    [ForeignKey("ProductID")]
    public virtual Product Product { get; set; } = null!;

}