import "./Income.css"
const transactions = [
  {
    title: "Deposit",
    date: "02/17/2023",
    amount: "$450.00",
    image:
      "data:image/png;base64,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",
  },
  // More transactions...
];

export default function Income() {
  return (
    <div className="income-section">
      <h1 className="user">Users</h1>
      <p className="content-detail">
        A list of all the incoming transactions in your account
      </p>

      <div className="">
        <div className="">
          <div className="table-container">
            <table className="income-table">
              <thead>
                <tr>
                  <th
                    scope="col"
                    className="table-heading"
                  >
                    Title
                  </th>
                  <th
                    scope="col"
                    className="table-heading"
                  >
                    Date
                  </th>
                  <th
                    scope="col"
                    className="table-heading"
                  >
                    Status
                  </th>
                </tr>
              </thead>
              <tbody className="">
                {transactions.map((transaction) => (
                  <tr key={transaction.amount}>
                    <td className="income-content">
                      <div className="table-content">
                        
                          <img
                            className="income-pic"
                            src={transaction.image}
                            alt=""
                          />
                        
                        <div className="">
                          <div className="income-text">
                            {transaction.title}
                          </div>
                          <div className="income-text">
                            {transaction.amount}
                          </div>
                        </div>
                      </div>
                    </td>
                    <td className="income-content">
                      <div className="income-text">{transaction.date}</div>
                    </td>
                    <td className="income-content">
                      <span className="income-text">
                        Complete
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}
